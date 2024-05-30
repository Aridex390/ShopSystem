import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { BrowserModule } from "@angular/platform-browser";
import { AppRouterModule } from "./app.routes";
import { AppComponent } from "./app.component";
import { HttpClientModule } from "@angular/common/http";


@NgModule({
    declarations: [AppComponent],
    imports: [
        CommonModule,
        BrowserModule,
        AppRouterModule,
    ],
    providers: [HttpClientModule],
    bootstrap: [AppComponent]
})

export class AppModule { }