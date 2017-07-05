// import * as types from './actionTypes';
import axios from 'axios';
import { pushState } from 'redux-react-router';

function getTask(data) {
	return {
		type: 'GET_TASK'
	}
};

function getTaskOk(json) {
	return{
		type: 'GET_TASK_OK',
		data: json
	}
};

function getTaskError(json) {
	return {
		type: 'GET_TASK_ERROR',
		data: json
	}
};

export function listarComandos(url, projeto) {
	return function(dispatch) {
		dispatch(getTask());
		return axios({
			url: url,
			params: {projetoId: projeto.id},
			auth: { username: 'user1' , password: 'secret1'},
			timeout: 20000,
			method: 'get',
			responseType: 'json'
		})
			.then(function(response) {
				dispatch(getTaskOk(response.data));
			})
			.catch(function(response){
				dispatch(getTaskError(response.data));
				dispatch(pushState(null,'/error'));
			})
	}
};

function getTaskId(data) {
	return {
		type: 'GET_TASK_ID'
	}
};

function getTaskIdOk(json) {
	return{
		type: 'GET_TASK_ID_OK',
		data: json
	}
};

function getTaskIdError(json) {
	return {
		type: 'GET_TASK_ID_ERROR',
		data: json
	}
};

export function getTaskById(url, task) {
	return function(dispatch) {
		dispatch(getTaskId());
		return axios({
			url: url + '/' + task,
			// params: {task},
			auth: { username: 'user1' , password: 'secret1'},
			timeout: 20000,
			method: 'get',
			responseType: 'json'
		})
			.then(function(response) {
				dispatch(getTaskIdOk(response.data));
			})
			.catch(function(response){
				dispatch(getTaskIdError(response.data));
				dispatch(pushState(null,'/error'));
			})
	}
};

function postData(data) {
	return {
		type: 'POST_DATA',
		data
	}
};

function postDataOk(response){
	return {
		type: 'POST_DATA_OK',
		response
	}
};

function postDataError(response){
	return {
		type: 'POST_DATA_ERROR',
		response
	}
};

export function executarComando(url, data) {
	return function(dispatch) {
		dispatch(postData(data));
		return axios({
			url: url,
			// params: {command: data},
			auth: { username: 'user1' , password: 'secret1'},
			timeout: 20000,
			method: 'post',
			data: data
		})
			.then(function(response) {
				dispatch(postDataOk(response.data));
			})
			.catch(function(response){
				dispatch(postDataError(response.data));
				dispatch(pushState(null,'/error'));
			})
	}
};

function getData() {
	return {
		type: 'GET_DATA',
		data: {}
	}
};

function getDataOk(json){
	return {
		type: 'GET_DATA_OK',
		data: json
	}
};

function getDataError(json){
	return {
		type: 'GET_DATA_ERROR',
		data: json
	}
};

export function getProjetos(url) {
	return dispatch => {
		dispatch(getData());
		return axios({
			url: url,
			auth: { username: 'user1' , password: 'secret1'},
			timeout: 20000,
			method: 'get',
			responseType: 'json'
		})
			.then(function(response) {
				dispatch(getDataOk(response.data));
			})
			.catch(function(response){
				dispatch(getDataError(response.data));
				dispatch(pushState(null,'/error'));
			})
	}
};
