'use strict';

angular.module('inspinia.controllers')
  .controller('PromocaoCriarController', function ($filter, $state, $rootScope, toastr, promocao, PromocaoService, FileUploader, APP_BASE_URL) {

    var vm = this;
    var method;

    //////////////////////////////////
    // Attributes
    //////////////////////////////////

    vm.promocao = promocao;
    vm.promocao.farmaciaId = 1;

    method = 'POST';
    if (promocao.id) {
      method = 'PUT';
    }
    var uploader = new FileUploader({
      url: APP_BASE_URL + "promocoes",
      method: method,
      formData: vm.promocao
    })

    vm.uploader = uploader;
    vm.uploader.filters.push({
      name: 'imageFilter',
      fn: function(item) {
          var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
          if('|jpg|png|jpeg|'.indexOf(type) !== -1){
              return true;
          }else{
              toastr.error('Por favor selecione imagens (jpg,png,jpeg) e com no máximo 400 KB');
              console.log(vm.uploader);
              return false;
          }
      }
    }, {
        name: 'sizeFilter',
        fn: function(item) {
            if (item.size > 409600) {
                toastr.error('Por favor selecione imagens (jpg,png,jpeg) e com no máximo 400 KB');
                return false;
            } else {
                return true;
            }
        }
    });

    //////////////////////////////////
    // Mocks
    //////////////////////////////////

    //////////////////////////////////
    // Métodos
    //////////////////////////////////

    vm.trataValor = function(valor) {
        valor = $filter('number')(valor, 2);
        valor = ("R$ ") + valor;
        vm.promocao.precoProduto = valor;
    }

    vm.salvar = function() {
      vm.form.$setSubmitted();
      if (vm.form.$valid) {
        vm.promocao.farmaciaId = $rootScope.farmacia.id;
        vm.trataValor(vm.promocao.precoProduto);

        vm.uploader.queue[0].url = APP_BASE_URL + ("promocoes?farmaciaId=" + vm.promocao.farmaciaId + "&nomeProduto=" + vm.promocao.nomeProduto + "&descricaoProduto=" + vm.promocao.descricaoProduto + "&precoProduto=" + vm.promocao.precoProduto);
        vm.uploader.queue[0].upload();

        // uploader.onSuccessItem = function(fileItem, response, status, headers) {
        uploader.onSuccessItem = function(fileItem, response, status) {
          if(fileItem.isSuccess && status === 200) {
            toastr.success('Cadastro efetuado com sucesso!');
            $state.go('promocao.listar');
          } else{
            toastr.error('Erro ao cadastrar promoção!');
          }

        }
      } else {
        toastr.error('Formulário inválido!');
      }
    };

    //////////////////////////////////
    // Watchers
    ////////////////////////////////// 

    //////////////////////////////////
    // Init
    //////////////////////////////////

  });
