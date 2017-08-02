(function() {
  'use strict';

  angular
    .module('inspinia.configs')
    .config(routerConfig);

  /** @ngInject */
  function routerConfig($stateProvider, $urlRouterProvider) {
    $stateProvider

      .state('index', {
        abstract: true,
        url: "/index",
        templateUrl: "app/components/common/content.html"
      })

      .state('index.home', {
        url: "/home",
        templateUrl: "app/features/home/home.html",
        controller: "HomeController",
        controllerAs: "vm",
        data: { pageTitle: 'Example view' }
      })

    $urlRouterProvider.otherwise('/index/home');
  }

})();

// 'use strict';
// angular.module('inspinia.configs').config(function($stateProvider) {
//   $stateProvider

//     .state("index", {
//       abstract: true,
//       url: "/index",
//       templateUrl: "app/components/common/content.html",
//       controllerAs: "vm"
//     })

//     .state('index.home', {
//       url: "/home",
//       templateUrl: "app/features/home/home.html",
//       controller: "HomeController",
//       controllerAs: "vm",
//       data: { pageTitle: 'Example view' }
//     })

//   // });
// });
