import { Product } from "./product.model";

export class OrderItem {
    id: string;
    quantity: number;
    product: Product;

    constructor(id: string, quantity: number, product: Product){
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    }
}