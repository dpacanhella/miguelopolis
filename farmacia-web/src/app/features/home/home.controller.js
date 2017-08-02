'use strict';

angular.module('inspinia.controllers')
	.controller('HomeController', function ($rootScope, $state) {

		var vm = this;

		vm.userName = 'Example user';
		vm.helloText = 'Bem vindo ao Guia Miguelópolis';
		vm.descriptionText = 'Utilize o menu ao lado para gerenciar suas promoções!';

		vm.logout = function() {
			$rootScope.logado = false;
			$state.go('login');
		};

	});
