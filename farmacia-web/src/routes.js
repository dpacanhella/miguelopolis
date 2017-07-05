import React from 'react';
import { Route, IndexRoute } from 'react-router';

// Containers
import Full from './containers/Full/'
// import Simple from './containers/Simple/'

import Dashboard from './views/Dashboard/'

export default (
    <Route path="/" name="Home" component={Full} >
      <IndexRoute component={Dashboard}/>
      <Route path="dashboard" name="Dashboard" component={Dashboard}/>
	  </Route>
);
