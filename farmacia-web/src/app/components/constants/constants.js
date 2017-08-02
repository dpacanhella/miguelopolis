'use strict';
angular.module('inspinia.constants')
	// .constant('APP_BASE_URL', 'http://45.55.209.136:8080/')
	.constant('SWEET_ALERT_CONFIRM_DEFAULTS', {
	  title: "Você têm certeza?",
	  text: "",
	  type: "warning",
	  showCancelButton: true,
	  confirmButtonColor: "#ec4c5a",
	  cancelButtonColor: "#566c72",
	  confirmButtonText: "Sim, confirmo",
	  cancelButtonText: "Não, cancela",
	  closeOnConfirm: true,
	  closeOnCancel: true
	});
angular.module('inspinia.constants').constant('APP_BASE_URL', '/api/');
// angular.module('inspinia.constants').constant('APP_BASE_URL', 'http://localhost:8080/');
