'use strict';

angular.module('inspinia.controllers')
  .controller('LoginController', function ($rootScope, $state, toastr, LoginService) {

    var vm = this;
    // vm.login = {usuario: 'nossasenhora2', senha: '653245'};

    vm.efetuarLogin = function() {
      vm.form.$setSubmitted();
      if (vm.form.$valid) {
        return LoginService.efetuarLogin(vm.login).then(function(response) {
          if(response.status == 200){
            $rootScope.logado = true;
            if(response.data.tipo == 'TIPO_FARMACIA'){
              $rootScope.farmacia = response.data
            } else{
              $rootScope.loja = response.data
            }
            $state.go('index.home');
          }
          else {
            $rootScope.logado = false;
            toastr.error('Erro ao fazer login!');
            // event.preventDefault();
          }
        });
      } 
    };
    
  });
