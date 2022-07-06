# Supermarket

### Story
We want to open a supermarket similar to Amazon Go.
The general flow is like this:
1) customer enters the shop 
2) checks in with an app
3) collects products to his cart (at this stage we assume that 
there is some system that knows how to assemble it to required format) 
4) On customers checkout the cart is getting calculated and the customer is informed about the bill size.

As a store we want to maintain some kind of catalog (look at the table). 
Every product in the catalog has:
* id/name
* price

Also every product can have a discount. There are two types of discounts:
1) X + Y 
    * i.e. product A has discount 2+1. 
    * That means that when a customer is taking 3 products A, only 2 of them are paid and 1 goes for free.
2) I -> J 
    * i.e. product B has a discount 3 for 50.
    * That means that instead of paying for every 3 B items 60 the customer will pay only 50.
    
The candidate should write the functionality of the automatic cashier where he decides:
 1) How (and if) to present a data (products/discounts/cart)
 2) How to calculate the discounts

### Table

| Product       | Price         | Discount      |
| ------------- | ------------- | ------------- |
| A             | 10            | X + Y i.e. (2 + 1)  |
| B             | 20            | I -> J i.e. (3 -> 50)  |
| C             | 30            |  |

### Suggestions
This task goes best when "roleplayed" as product owner (interviewer) and engineer (candidate). 

### Caveats
 Partial discount
 * As a cashier is automatic the customer can take only partial discount of the X + Y.
 * For example: the discount is 3 + 2 and the customer took 4 in this case we still want him to pay for only 3 (X) items and the last one he will get for free.
 * The case can be mentioned at the presentation stage (better) or introduced at the later stage as a complication.  

### Common Mistakes
* Data modelling is messy or missing. Some people are trying to assemble things on the fly, but I have never seen someone to actually succeeding.
* Breaking separation of concern and encapsulation rules. i.e. Catalog objects used in cart to store number of items.
* To calculate the number of X + Y bundles only X is used for division/mod. Obviously in this case the number of bundles will scale disproportionally. 
