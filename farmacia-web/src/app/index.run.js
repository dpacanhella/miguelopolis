(function() {
  'use strict';

  angular
    .module('inspinia')
    .run(runBlock);

  /** @ngInject */
  function runBlock($log, $rootScope, $location, $state) {
    // $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState){
    var checkRoute = $rootScope.$on('$stateChangeStart', function(event, toState){
      if(!$rootScope.logado && toState.name != 'login') {//Sua regra aqui.
        event.preventDefault(); // Utilizando esse método você PARA o ciclo natural, ou seja não vai mais executar a chamada da rota, assim você pode fazer seu tratamento.
        $state.go('login');
      }else if($rootScope.logado && toState.name == 'login') {
        $state.go('index.home');
      }
		});
    $rootScope.$on('$destroy', checkRoute);

    // $log.debug('runBlock end');
  }

})();