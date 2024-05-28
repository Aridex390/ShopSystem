import { Product } from "./product.model";

export class PagedResponse {
    products: Product[] | undefined;
    count: number | undefined;
    totalCount: number | undefined;
}