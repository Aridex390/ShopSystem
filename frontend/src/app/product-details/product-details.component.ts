import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ProductService } from '../_service/product.service';
import { UserCartService } from '../_service/user-cart.service';
import { Product } from '../_models/product.model';
import { ActivatedRoute } from '@angular/router';
import { PagedResponse } from '../_models/paged-response.model';
import { NgFor } from '@angular/common';

const SIZE: number = 6;
@Component({
  selector: 'app-product-details',
  standalone: true,
  imports: [
    RouterLink,
    NgFor,
  ],
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})

export class ProductDetailsComponent implements OnInit {
  
  productId: string = '1';
  productCategory: string = 'PANTS';

  product: Product = new Product;
  products: Product[] = [];
  

  constructor(private productService: ProductService,
              private userCartService: UserCartService,
              private activeRoute: ActivatedRoute
  ){}
  
  ngOnInit(): void {
    this.activeRoute.paramMap.subscribe(params => {
      this.productId = params.get('id') ?? '';
    });
  

    this.productService.findProductById(this.productId).subscribe((response: Product) => {
      this.product = response;
      this.productCategory = response.category;
    })

    console.error(this.productCategory);

    this.productService.findProductsByCategory(this.productCategory, SIZE).subscribe((response: Product[]) => {
      this.products = !!response ? response: [];
    });
  }



}
