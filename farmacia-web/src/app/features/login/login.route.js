(function() {
  'use strict';

  angular
    .module('inspinia.configs')
    .config(routerConfig);

  /** @ngInject */
  function routerConfig($stateProvider, $urlRouterProvider) {
    $stateProvider

      .state("login", {
        url: "/",
        templateUrl: "app/features/login/login.html",
        controller: "LoginController",
        controllerAs: "vm",
        data: {
          pageTitle: 'Login', specialClass: 'gray-bg' 
        }
      });

    $urlRouterProvider.otherwise('/login');
  }

})();