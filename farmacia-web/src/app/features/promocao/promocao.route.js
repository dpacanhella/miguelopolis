(function() {
  'use strict';

  angular
    .module('inspinia.configs')
    .config(routerConfig);

  /** @ngInject */
  function routerConfig($stateProvider, $urlRouterProvider) {
    $stateProvider

      .state('promocao', {
        abstract: true,
        url: "/index",
        templateUrl: "app/components/common/content.html"
      })

      .state('promocao.listar', {
        url: "/promocao",
        templateUrl: "app/features/promocao/listar/promocao-listar.html",
        controller: "PromocaoListarController",
        controllerAs: "vm",
        data: { restrict: true },
        resolve: {
          promocoes: function($rootScope, PromocaoService) {
            return PromocaoService.getPromocoesByFarmacia($rootScope.farmacia.id);
          }
        }
      })

      .state('promocao.criar', {
        url: "/promocao/novo",
        templateUrl: "app/features/promocao/criar/promocao-criar.html",
        controller: "PromocaoCriarController",
        controllerAs: "vm",
        data: { restrict: true },
        resolve: {
          promocao: function() {
            return {};
          }
        }
      })

      .state('promocao.editar', {
        url: "/promocao/:id",
        templateUrl: "app/features/promocao/criar/promocao-criar.html",
        controller: "PromocaoCriarController",
        controllerAs: "vm",
        data: { restrict: true }
      })

    $urlRouterProvider.otherwise('/promocao');
  }

})();