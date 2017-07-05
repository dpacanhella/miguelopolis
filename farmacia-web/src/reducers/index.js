// import * as types from '../actions/actionTypes';
import { combineReducers } from 'redux'; //might need to remove
//TODO: o que essas duas libs fazem de diferente?
import { routerStateReducer }  from 'redux-react-router';
import { routerReducer } from 'react-router-redux';

//REDUCERS

const rootReducer = combineReducers({
	router: routerStateReducer,
	routing: routerReducer,
});

export default rootReducer;