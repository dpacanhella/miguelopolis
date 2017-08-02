'use strict';

angular.module('inspinia.services')
  .service('PromocaoService', function($http, FileUploader, APP_BASE_URL) {
    return {
      
      getPromocoesByFarmacia: function(id) {
        return $http({
          url: APP_BASE_URL + ("promocoes/farmaciaId/" + id),
          method: 'GET'
        }).then(function(result) {
          return result.data;
        });
      }
  }
});
