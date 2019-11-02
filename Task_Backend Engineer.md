# Backend Developer Task
We pride ourselves in being pragmatic and understand that everyone works best on their own way. That's why we believe that a take at home task is a much better way to gauge your technical skills in the interview process than a whiteboard problem.

Some friendly tips:
- We're not looking for a particular way to solve the tasks. It's completely up to you how you solve them given the requirements below. Also, there's no specific library or frameworks requirements besides the programming language.
- Please include a `README.md` or similar explaining us how to use your solutions.

## 1. Currency conversion server
> Please solve in Scala or Java.

Make sure we can build and run your program easily (but do not send in compiled solutions). Implement a server-side currency converter, which converts Euro, Dollars, and Yen in all directions. Please use exchange rates from a publicly available service and expose your service as an HTTP API.

## 2. Currency conversion client
> Please solve in your preferred scripting language, except Bash ;)

Implement a CLI script that takes a JSON file and a target currency as input and outputs a JSON file with all monetary values converted into the given target currency using the currency conversion API created in task 1.

For example:

Given an input file with the following structure (one JSON object per line):
```json
{ "item": "B", "price": 9.95, "currency": "USD" }
{ "item": "A", "price": 99.95, "currency": "JPY" }
{ "item": "C", "price": 10.99, "currency": "EUR" }
```

And `EUR` as the target currency for your script, the following could be the resulting file (the specific conversion rate is not relevant):
```json
{ "item": "B", "price": 8.94, "currency": "EUR" }
{ "item": "A", "price": 0.83, "currency": "EUR" }
{ "item": "C", "price": 10.99, "currency": "EUR" }
```

## 3. Share your thoughts
> Your implementation doesn't need to handle the following, just explain your thoughts on the matter.

Please give a short description of the following characteristics for your solution:

- Will it work with a 2 TB input file as well?
- What happens if the input file has one malformed line towards the end of the file?
- Is it fine to encode the price value as a number in JSON? What kind of problems could arise?
