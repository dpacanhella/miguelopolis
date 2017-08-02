angular.module "inspinia", ['inspinia.vendors', 'inspinia.scripts']
  .config ($stateProvider, $urlRouterProvider, APP_OTHERWISE_URL) ->

    $urlRouterProvider.otherwise APP_OTHERWISE_URL
    $urlRouterProvider.when '/home', APP_OTHERWISE_URL

  .config ($translateProvider) ->
    $translateProvider.useStaticFilesLoader {
      type: 'static-files'
      prefix: 'i18n/'
      suffix: '.json'
    }

    $translateProvider.preferredLanguage('pt-BR')

  # .run ($rootScope) ->
  #   $rootScope.$on '$stateChangeSuccess', ->
  #     $("html, body").animate({ scrollTop: 0 }, 0);
  #     # $.noty.closeAll()

######################
# Modules
######################
angular.module 'inspinia.services'   , []
angular.module 'inspinia.factories'  , []
angular.module 'inspinia.directives' , []
angular.module 'inspinia.filters'    , []
angular.module 'inspinia.controllers', []
angular.module 'inspinia.constants'  , []
angular.module 'inspinia.configs'    , []
# angular.module 'inspinia.providers'  , []
angular.module 'inspinia.templates'  , []


######################
# Vendors
######################
angular.module 'inspinia.vendors', [
  'ngAnimate'
  'ngCookies'
  'ngTouch'
  'ngSanitize'
  'ngMessages'
  'ngAria'
  'ngResource'
  'ui.router'
  'ui.bootstrap'
]

######################
# Scripts
######################
angular.module 'inspinia.scripts', [
  'inspinia.services'
  'inspinia.factories'
  'inspinia.directives'
  'inspinia.filters'
  'inspinia.controllers'
  'inspinia.constants'
  'inspinia.configs'
  # 'inspinia.providers'
  'inspinia.templates'
]