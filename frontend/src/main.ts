import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import jquery from 'jquery';

bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));


class Main {
  constructor() {
    this.initializeEvents();
  }

  private initializeEvents(): void {
    jquery(window).on('load', this.handleWindowLoad);
    jquery('.filter__controls li').on('click', this.handleFilterControlClick);
    jquery('.search-switch').on('click', this.handleSearchSwitchClick);
    jquery('.search-close-switch').on('click', this.handleSearchCloseSwitchClick);
    jquery('.canvas__open').on('click', this.handleCanvasOpenClick);
    jquery('.offcanvas-menu-overlay').on('click', this.handleOffcanvasMenuOverlayClick);
    jquery('.product__color__select label, .shop__sidebar__size label, .product__details__option__size label').on('click', this.handleRadioBtnClick);
  }
  private handleWindowLoad = (): void => {
    $('.loader').fadeOut();
    $('#preloder').delay(200).fadeOut('slow');

    if ($('.product__filter').length > 0) {
      const containerEl = document.querySelector('.product__filter');
      //const mixer = mixitup(containerEl);
    }

    $('.set-bg').each((index, element) => {
      const bg = $(element).data('setbg');
      $(element).css('background-image', `url(${bg})`);
    });

    /*$('.mobile-menu').slicknav({
      prependTo: '#mobile-menu-wrap',
      allowParentLinks: true
    }); */

    /*$('.hero__slider').owlCarousel({
      loop: true,
      margin: 0,
      items: 1,
      dots: false,
      nav: true,
      navText: ["<span class='arrow_left'><span/>", "<span class='arrow_right'><span/>"],
      animateOut: 'fadeOut',
      animateIn: 'fadeIn',
      smartSpeed: 1200,
      autoHeight: false,
      autoplay: false
    }); */

    //jquery('select').niceSelect();

    /*$('.nice-scroll').niceScroll({
      cursorcolor: '#0d0d0d',
      cursorwidth: '5px',
      background: '#e5e5e5',
      cursorborder: '',
      autohidemode: true,
      horizrailenabled: false
    });*/

    const today = new Date();
    const dd = String(today.getDate()).padStart(2, '0');
    const mm = String(today.getMonth() + 1).padStart(2, '0');
    let yyyy = today.getFullYear();

    /*if (mm === '12') {
      mm = '01';
      yyyy += 1;
    } else {
      mm = parseInt(mm) + 1;
      mm = String(mm).padStart(2, '0');
    }*/

    const timerdate = `${mm}/${dd}/${yyyy}`;

    /*$('#countdown').countdown(timerdate, (event) => {
      $(this).html(event.strftime("<div class='cd-item'><span>%D</span> <p>Days</p> </div>" + "<div class='cd-item'><span>%H</span> <p>Hours</p> </div>" + "<div class='cd-item'><span>%M</span> <p>Minutes</p> </div>" + "<div class='cd-item'><span>%S</span> <p>Seconds</p> </div>"));
    }); 

    $('.video-popup').magnificPopup({
      type: 'iframe'
    }); */

    const proQty = $('.pro-qty');
    proQty.prepend('<span class="fa fa-angle-up dec qtybtn"></span>');
    proQty.append('<span class="fa fa-angle-down inc qtybtn"></span>');
    proQty.on('click', '.qtybtn', this.handleQuantityChange);

    const proQty2 = $('.pro-qty-2');
    proQty2.prepend('<span class="fa fa-angle-left dec qtybtn"></span>');
    proQty2.append('<span class="fa fa-angle-right inc qtybtn"></span>');
    proQty2.on('click', '.qtybtn', this.handleQuantityChange);

    $('.cn_num').each((index, element) => {
      $(element).prop('Counter', 0).animate({
        Counter: $(element).text()
      }, {
        duration: 4000,
        easing: 'swing',
        step: (now) => {
          $(element).text(Math.ceil(now));
        }
      });
    });
  }

  private handleFilterControlClick = (): void => {
    $('.filter__controls li').removeClass('active');
    $(this).addClass('active');
  }

  private handleSearchSwitchClick = (): void => {
    $('.search-model').fadeIn(400);
  }

  private handleSearchCloseSwitchClick = (): void => {
    $('.search-model').fadeOut(400, () => {
      $('#search-input').val('');
    });
  }

  private handleCanvasOpenClick = (): void => {
    $('.offcanvas-menu-wrapper').addClass('active');
    $('.offcanvas-menu-overlay').addClass('active');
  }

  private handleOffcanvasMenuOverlayClick = (): void => {
    $('.offcanvas-menu-wrapper').removeClass('active');
    $('.offcanvas-menu-overlay').removeClass('active');
  }

  private handleRadioBtnClick = (): void => {
    $('.product__color__select label, .shop__sidebar__size label, .product__details__option__size label').removeClass('active');
    $(this).addClass('active');
  }

  private handleQuantityChange = (): void => {
    const $button = $(this);
    const oldValue = $button.parent().find('input').val();
    let newVal;

    /*if ($button.hasClass('inc')) {
      newVal = parseFloat(oldValue) + 1;
    } else {
      if (oldValue > 0) {
        newVal = parseFloat(oldValue) - 1;
      } else {
        newVal = 0;
      }
    }

    $button.parent().find('input').val(newVal); */
  }
}

export default Main;
  
