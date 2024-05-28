import { Component, OnInit, EventEmitter, Input, Output } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ProductService } from '../_service/product.service';
import { Product } from '../_models/product.model';
import { MatPaginatorModule, PageEvent} from '@angular/material/paginator';
import { PagedResponse } from '../_models/paged-response.model';

@Component({
  selector: 'app-shop',
  standalone: true,
  imports: [RouterLink, MatPaginatorModule],
  templateUrl: './shop.component.html',
  styleUrl: './shop.component.css'
})
export class ShopComponent implements OnInit {
  
  page: number = 0;
  size: number = 12;
  totalCount: number = 0;

  products: Product[] = [];

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.productService.refreshProducts(this.page, this.size).subscribe((response: PagedResponse) => {
      this.products = !!response.products ? response.products : [];
      this.totalCount = !!response.totalCount ? response.totalCount : 0;
    });
  }

  loadProducts(event: PageEvent): PageEvent {
    this.page = event.pageIndex;
    this.size = event.pageSize;

    this.productService.refreshProducts(this.page, this.size).subscribe((response: PagedResponse) => {
      this.products = !!response.products ? response.products : [];
      this.totalCount = !!response.totalCount ? response.totalCount : 0;
    });

    return event
  }
}
