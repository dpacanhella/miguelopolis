'use strict';

angular.module('inspinia.controllers')
  .controller('PromocaoListarController', function ($scope, $rootScope, promocoes, toastr, SweetAlert, SWEET_ALERT_CONFIRM_DEFAULTS, PromocaoService) {

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

    vm.refresh = function() {
      return $scope.$broadcast("rsDatagrid:refresh");
    };

    vm.confirmCallDeletar = function(id, isConfirm) {
      if (isConfirm) {
        return PromocaoService.deletarPromocao(id).then(function(response) {
          if (response.status === 200) {
            toastr.success('Promoção excluída com sucesso!');
          } else {
            toastr.error('Erro ao excluir promoção!');
          }
          getPromocoesFarmacia($rootScope.farmacia.id);
        });
      }
    };

    var getPromocoesFarmacia = function(id) {
      return PromocaoService.getPromocoesByFarmacia(id).then(function(response) {
        vm.promocoes = response;
        return vm.refresh();
      });
    };

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
        }, {
          title: 'Foto do produto',
          index: 'imagem',
          sort: false,
          isHtml: true,
          class: 'text-center',
          render: function(row) {
            return "<img src="+row.imagemProduto+ " height='70' width='70' />"
          }
        },{
          title: 'Ação',
          index: 'acao',
          class: 'text-center',
          sort: false,
          style: {
            width: '3%',
          }
        }
      ],
      buttons: [
        {
          tooltip: 'Excluir',
          classButton: 'btn btn-danger',
          classIcon: 'fa fa-trash',
          onClick: function(row) {
            console.log(row.id);
            var alertConfig, msg;
            msg = "Está ação irá deletar esta promoção!";
            alertConfig = _.extend(SWEET_ALERT_CONFIRM_DEFAULTS, {
              "text": msg
            });
            return SweetAlert.swal(alertConfig, function(isConfirm) {
              return vm.confirmCallDeletar(row.id, isConfirm);
            });
          }
        }
      ],
      data: function(){
        return vm.promocoes
      }
    };

    //////////////////////////////////
    // Watchers
    ////////////////////////////////// 

    $scope.$on('$stateChangeStart', function() {
      return SweetAlert.close();
    });

    //////////////////////////////////
    // Init
    //////////////////////////////////

  });
