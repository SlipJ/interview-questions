class Discount:
    def __init__(self, base_amount: int, bonus_amount: int, bundle_price: int = None):
        self.base_amount = base_amount
        self.bonus_amount = bonus_amount
        self.bundle_price = bundle_price

    def calculate(self, amount: int, price_per_item: int):
        if not self.bundle_price:
            self.bundle_price = self.base_amount * price_per_item
        bundles_num = amount // self.base_amount + self.bonus_amount
        rem = amount % self.base_amount + self.bonus_amount
        if rem > self.base_amount:
            bundles_num += 1
            rem = 0
        return bundles_num * self.bundle_price + rem * price_per_item


class Product:
    def __init__(self, product_id: str, price: int, discount: Discount = None):
        self.product_id = product_id
        self.price = price
        self.discount = discount

    def calculate(self, amount: int):
        if self.discount:
            return self.discount.calculate(amount, self.price)
        return amount * self.price


class CartElement:
    def __init__(self, product: Product, amount: int):
        self.product = product
        self.amount = amount


a_disc = Discount(2, 1, 20)  # When we create the discount we now the relevant product and can count bundle price once but we don't have to
b_disc = Discount(3, 0, 50)  # The bonus is 0

A = Product('A', 10, a_disc)
B = Product('B', 20, b_disc)
cart = [CartElement(A, 5), CartElement(B, 7)]


def main(cart):
    prices = [elm.product.calculate(elm.amount) for elm in cart]
    return sum(prices)
