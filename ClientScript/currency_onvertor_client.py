import sys
import os.path
import json
import urllib2

CURRENCY_LIST = ['USD', 'JPY', 'EUR']
DEFAULT_INPUT_FILE = "input.json"
OUT_FILE = "output.json"
URL = "http://localhost:8080/currency/v1/convert?from=:from&to=:to&amount=:amount"


def read_input_file_name():
    """ Reads the input file from REPL """
    file_name = raw_input("Please input the file name, or enter . to read from input.json from current directory: ")
    if not file_name.strip():
        print "File name cannot be blank"

    file_name = " ".join(file_name.split())

    if file_name == ".":
        file_name = DEFAULT_INPUT_FILE

    if not os.path.exists(file_name):
        print "Input file not found, Exiting from application"
        sys.exit(-1)

    return file_name


FILE_NAME = read_input_file_name()
print "Reading from file : " + FILE_NAME


def validate_currency(currency):
    """ currency type validation """
    if currency not in CURRENCY_LIST:
        return False
    return True


def read_currency():
    """ Reading the target currency type """
    target_currency = raw_input("Please input the target currency : ")
    if not target_currency.strip():
        print "Target currency is required"
    target_currency = " ".join(target_currency.split())
    target_currency.upper()

    while True:
        if validate_currency(target_currency):
            break
        else:
            print ', '.join(CURRENCY_LIST) + " are supported for conversion"
            print "Please try again !"
            target_currency = read_currency()

    return target_currency


TARGET_CURRENCY = read_currency()


def convert_currency(source_currency, target_currency, amount):
    """ converting the currency from one type to another """
    url = URL.replace(":from", source_currency).replace(":to", target_currency).replace(":amount", amount)
    try:
        response = urllib2.urlopen(url)
        converted_amount = response.read()
    finally:
        response.close()
    return converted_amount

try:
    os.remove(OUT_FILE)
except:
    pass

out_file = open(OUT_FILE, "a")

""" processing the IO operations """
records_count = 0
with open(FILE_NAME, "r") as f:
    for line in f:
        records_count = records_count + 1
        try:
            json_data = json.loads(line)
            converted_price = convert_currency(json_data["currency"], TARGET_CURRENCY, str(json_data["price"]))
            json_data["currency"] = TARGET_CURRENCY
            json_data["price"] = converted_price
            out_file.write("{}\n".format(json.dumps(json_data)))
        except:
            print "Exception processing data " + line

out_file.close()
print str(records_count) + " records processed"
