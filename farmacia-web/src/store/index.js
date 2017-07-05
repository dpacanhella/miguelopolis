import { createStore, applyMiddleware } from 'redux';

import { syncHistoryWithStore} from 'react-router-redux';
import { browserHistory } from 'react-router';
import rootReducer from '../reducers';
import thunk from 'redux-thunk';
import { createLogger } from 'redux-logger';
import { composeWithDevTools } from 'redux-devtools-extension';

const middleware = [ thunk ]
if (process.env.NODE_ENV !== 'production') {
  middleware.push(createLogger())
}

function configureStore() {
	return createStore(
	  rootReducer, composeWithDevTools(
	  applyMiddleware(...middleware),
	));
}

const store = configureStore();

export const history = syncHistoryWithStore(browserHistory, store);

export default store;