export class Product {
    id: string;
    name: string;
    description: string;
    price: number;
    currency: string;
    category: string;

    constructor(id: string, name: string, description: string, price: number, currency: string, category: string) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.category = category;
    }
}