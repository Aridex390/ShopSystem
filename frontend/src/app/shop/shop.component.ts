import { Component, NO_ERRORS_SCHEMA, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ProductService } from '../_service/product.service';
import { Product } from '../_models/product.model';
import { MatPaginatorModule, PageEvent} from '@angular/material/paginator';
import { PagedResponse } from '../_models/paged-response.model';
import { NgFor } from '@angular/common';
import { MatInput } from '@angular/material/input';
import { MatSelect } from '@angular/material/select';
import { MatFormField } from '@angular/material/form-field';



interface SortingRequest {
  value: string | undefined;
  viewValue: string | undefined;
}
@Component({
  selector: 'app-shop',
  standalone: true,
  imports: [
    RouterLink,
    MatPaginatorModule,
    NgFor,
    MatInput,
    MatSelect,
    MatFormField
  ],
  templateUrl: './shop.component.html',
  styleUrl: './shop.component.css',
  schemas: [NO_ERRORS_SCHEMA]
})
export class ShopComponent implements OnInit {
  
  page: number = 0;
  size: number = 12;
  totalCount: number = 0;

  products: Product[] = [];
  sortings: SortingRequest[] = [];
  
  constructor(private productService: ProductService) {}
  
  ngOnInit(): void {
    this.productService.refreshProducts(this.page, this.size).subscribe((response: PagedResponse) => {
      this.products = !!response.products ? response.products : [];
      this.totalCount = !!response.totalCount ? response.totalCount : 0;
    });
    
    this.sortings = [
      {value: 'price_low_high-0', viewValue: 'price from low to high'},
      {value: 'price_high_low-1', viewValue: 'price from high to low'},
      {value: 'name_a_z-3', viewValue: 'name A to Z'},
      {value: 'name_z_a-4', viewValue: 'name Z to A'},
    ];
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
