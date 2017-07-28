# redspark ios commons

Esse repositório tem como objetivo centralizar todas as bibliotecas geradas para iOS.

## Configurando o repositório privado redspark
No terminal inserir o seguinte comando.
```
$ pod repo add redspark-ios-specs https://github.com/redspark-io/redspark-ios-specs.git
```

Validando se seu repositório foi criado corretamente.
```
$ cd ~/.cocoapods/repos/redspark-ios-specs
$ pod repo lint .
```

## Criando uma nova biblioteca

1 - Na pasta raiz do repositório inserir o comando.

``` pod lib create <nome da lib> ```

sendo que o nome da lib deverá seguir o padrão redpsark-ios-<função> como: redspark-ios-social   
    
2 - Na questão **What language do you want to use?? [ ObjC / Swift ]**,  Escolher a linguagem de programação obj-c ou swift.    
3 - Na questão **Would you like to include a demo application with your library? [ Yes / No ]**, escolher YES para criar uma aplicação para teste da biblioteca.      
4 - Na questão **Which testing frameworks will you use? [ Quick / None ]**, escolher None para utilizar as ferramentas de teste padrão do xCode.     
5 - Na questão **Would you like to do view based testing? [ Yes / No ]**, escolhe No.     
     
Após finalizado o xCode deverá abrir com o projeto exemplo e a biblioteca já configurada.    
    
6 - Copiar o arquivo *.podspec* da pasta do projeto para a pasta raiz do git **redspark-ios-commons**    
7 - No xCode, deletar o arquivo *.podspec* da pasta **Podspec Metadata** e referenciar o mesmo arquivo novamente com o endereço correto.        
8 - Alterar o arquivo *.podspec* de acordo com os dados do projeto, conforme exemplo:         
     
```    
#      
# Be sure to run `pod lib lint spreadsheet-view.podspec' to ensure this is a     
# valid spec before submitting.     
#      
# Any lines starting with a # are optional, but their use is encouraged    
# To learn more about a Podspec see http://guides.cocoapods.org/syntax/podspec.html   
#     
     
Pod::Spec.new do |spec|    
    
#     
# https://guides.cocoapods.org/syntax/podspec.html#specification    
#    
    
### Root specification    
    spec.name             = "RSSpreadsheetView"    
    spec.version          = "1.0.3"    
    spec.summary          = "CollectionView com scroll bi-dimencional"    
    spec.homepage         = "https://github.com/redspark-io/redspark-ios-commons.git"    
    spec.author           = { "Marcus Costa" => "marcus.costa@redspark.io" }    
    spec.license          = { :type => 'MIT', :file => 'spreadsheet-view/LICENSE' }    
    spec.description      = <<-DESC    
    CollectionView com scroll bi-dimencional, inclui possibilidade de travar linhas e colunas.    
    Modalidade de swipe no conteúdo que rasteriza a posição do touch com relação a célula.    
    DESC    
    
    spec.source           = { :git => "https://github.com/redspark-io/redspark-ios-commons.git", :tag => 'RSSpreadsheetView-v'+String(spec.version) }     
    
### Platform    
    spec.ios.deployment_target = '8.0'    
    
### Build settings    
    spec.requires_arc = true    
    spec.frameworks = 'UIKit'    
    spec.module_name = 'RSSpreadsheetView'    
     
### File patterns   
    spec.source_files = 'RSSpreadsheetView/spreadsheet-view/Classes/**/*'    
    #spec.resource_bundles = {    
    #   'spreadsheet-view' => ['RSSpreadsheetView/spreadsheet-view/Assets/*.png']    
    #}    
    
end     
```    
     
Sendo que deve-se ter uma atenção especial para a tag do source que deverá conter sempre o nome do módulo de sua biblioteca       
```spec.source           = { :git => "https://github.com/redspark-io/redspark-ios-commons.git", :tag => 'RSSpreadsheetView-v'+String(spec.version) }```     
     
e para os File patters onde deverá ser inserida as informações do local onde encontra-se a pasta do seu projeto relativa a pasta onde se encontra o arquivo *.podspec           
```     
### File patterns     
    spec.source_files = 'RSSpreadsheetView/spreadsheet-view/Classes/**/*'   
    #spec.resource_bundles = {     
    #   'spreadsheet-view' => ['RSSpreadsheetView/spreadsheet-view/Assets/*.png']     
    #}     
     
```     
     
     
9 - No arquivo **Podfile**, dentro do target Pods, alterar o path para '../../' conforme exemplo:    
    
```
use_frameworks!

    target 'spreadsheet-view_Example' do
    pod 'spreadsheet-view', :path => '../../'

        target 'spreadsheet-view_Tests' do
        inherit! :search_paths


end


```
     
10 - Criar um arquivo CHANGELOG.md na pasta **Podspec Metadata** para armazenar todas as modificações realizadas em sua biblioteca, conforme exemplo:      
      
```
# 1.0.0

* Criação da biblioteca
* [add] compartilhamento nativo com facebook
* [add] compartilhamento nativo com twitter
* [add] action sheet para escolha de compartilhamento

```
    
## Publicação da biblioteca     
    
1 - Alterar a versão no arquivo *.podspec **spec.version = "x.x.x"**          
2 - Após realizados todos os testes deve-se realizar o push dos dados para o repositório    
```$ git push origin master```
     
3 - Criar uma tag com o nome do módulo seguido da versão conforme especificado na tag de source do pod spec, sendo o padrão <module name>-vx.x.x   
**RSSocial-v1.0.0**

```git tag -a <tag> -m "<tag>"```
    
4 - Enviar a tag para git   
```git push origin --tag``` 
     
5 - Validar a spec para submeter para o repositório      
```pod spec lint --private <file name>.podspec --verbose```     
     
6 - Submeter a biblioteca para o repositório privado     
```pod repo push redspark-ios-specs <file name>.podspec --verbose```     


