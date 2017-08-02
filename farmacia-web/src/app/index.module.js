(function() {
  'use strict';
  angular.module('inspinia', ['inspinia.vendors', 'inspinia.scripts']);

  })();

  angular.module('inspinia.services', []);

	angular.module('inspinia.factories', []);

	angular.module('inspinia.directives', []);

	angular.module('inspinia.filters', []);

	angular.module('inspinia.controllers', []);

	angular.module('inspinia.constants', []);

	angular.module('inspinia.configs', []);

	angular.module('inspinia.templates', []);

	angular.module('inspinia.vendors', ['ngAnimate', 'ngCookies', 'ngTouch', 'ngSanitize', 'ngMessages', 'ngAria', 'ngResource', 'ui.router', 'ui.bootstrap', 'rs.datagrid', 'toastr', 'angularFileUpload']);
	
	angular.module('inspinia.scripts', ['inspinia.services', 'inspinia.factories', 'inspinia.directives', 'inspinia.filters', 'inspinia.controllers', 'inspinia.constants', 'inspinia.configs', 'inspinia.templates']);