import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';

import { ShopComponent } from './shop/shop.component';
import { BlogComponent } from './blog/blog.component';
import { BlogDetailsComponent } from './blog-details/blog-details.component';
import { AboutComponent } from './about/about.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { ContactComponent } from './contact/contact.component';
import { ShopDetailsComponent } from './shop-details/shop-details.component';
import { ShoppingcartComponent } from './shoppingcart/shoppingcart.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { HomeComponent } from './home/home.component';
import { ProductDetailsComponent } from './product-details/product-details.component';

export const routes: Routes = [
    {path: '', redirectTo: '/home', pathMatch: 'full'},
    {path: 'home', component: HomeComponent},
    {path: 'shop/:page', component: ShopComponent},
    {path: 'shop/product-details/:id', component: ProductDetailsComponent},
    {path: 'blog', component: BlogComponent},
    {path: 'checkout', component: CheckoutComponent},
    {path: 'contact', component: ContactComponent},
    {path: 'about', component: AboutComponent},
    {path: 'blogDetails', component: BlogDetailsComponent},
    {path: 'shopDetails', component: ShopDetailsComponent},
    {path: 'shoppingCart', component: ShoppingcartComponent},
    {path: 'signIn', component: SignInComponent},
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})

export class AppRouterModule {}