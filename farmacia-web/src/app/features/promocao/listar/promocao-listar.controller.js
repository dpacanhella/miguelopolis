'use strict';

angular.module('inspinia.controllers')
  .controller('PromocaoListarController', function (promocoes) {

    var vm = this;

    //////////////////////////////////
    // Attributes
    //////////////////////////////////

    vm.promocoes = promocoes;

    //////////////////////////////////
    // Mocks
    //////////////////////////////////

    //////////////////////////////////
    // Métodos
    //////////////////////////////////

    vm.options = {
      classTable: 'table table-bordered table-striped',
      messageLoading: 'Loading...',
      messageEmpty: 'Nenhum resultado encontrado',
      sort: true,
      defaultSort: 'id,asc',
      search: {
        label: 'Filtrar por: '
      },
      collumns: [
        {
          title: 'Código',
          index: 'codigo',
          render: function(row) {
            return row.id;
          }
        }, {
          title: 'Nome do produto ',
          index: 'nomeProduto'
        }, {
          title: 'Descrição',
          index: 'precoInicial'
        }, {
          title: 'Preço',
          index: 'precoFinal'
        }
      ],
      data: function(){
        return vm.promocoes
      }
    };

    //////////////////////////////////
    // Watchers
    ////////////////////////////////// 

    //////////////////////////////////
    // Init
    //////////////////////////////////

  });
