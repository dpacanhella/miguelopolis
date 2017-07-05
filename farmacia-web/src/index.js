import React from 'react';
import ReactDOM from 'react-dom';
import { Router } from 'react-router';
import routes from './routes';

// import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import 'react-table/react-table.css'
import { ReactTableDefaults } from 'react-table'
import { Provider } from 'react-redux';
import store, {history} from './store';

Object.assign(ReactTableDefaults, {
  defaultPageSize: 10,
  pageSizeOptions: [10, 15, 20, 25],
  previousText: 'Anterior',
  nextText: 'Próximo',
  loadingText: 'Carregando...',
  noDataText: 'Nenhum item encontrado',
  pageText: 'Página',
  ofText: 'de',
  rowsText: 'itens',
  minRows: 3,
})


ReactDOM.render(
	<Provider store={store}>
  	<Router routes={routes} history={history} />
  </Provider>, document.getElementById('root')
);
