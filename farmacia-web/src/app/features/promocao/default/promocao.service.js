'use strict';

angular.module('inspinia.services')
  .service('PromocaoService', function($http, APP_BASE_URL) {
    return {
      
      getPromocoesByFarmacia: function(id) {
        return $http({
          url: APP_BASE_URL + ("promocoes/farmaciaId/" + id),
          method: 'GET'
        }).then(function(result) {
          return result.data;
        });
      },

      deletarPromocao: function(id) {
        return $http({
          url: APP_BASE_URL + ("promocoes/" + id),
          method: 'DELETE'
        });
      }
  }
});
