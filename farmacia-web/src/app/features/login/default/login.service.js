'use strict';

angular.module('inspinia.services')
  .service('LoginService', function($http, APP_BASE_URL) {
    return {

      efetuarLogin: function(login) {
        return $http({
          url: APP_BASE_URL + ("login"),
          method: 'GET',
          params: { login: login.usuario, password: login.senha }
        }).then(function successCallback(response) {
          return(response);
        }, function errorCallback(response) {
          return(response)
          // toastr.error('Erro ao fazer login!');
        });
      }

    };
  }
);