/**
 * HeaderComponent.
 *
 * Renders a Bootstrap-Jumbotron.
 */

'use strict';

import React from 'react';

require('styles//Header.css');

let HeaderComponent = (props) => (
        <div className="header-component">
        <div className="jumbotron">
        </div>
        </div>
);

HeaderComponent.displayName = 'HeaderComponent';

HeaderComponent.propTypes = {};
HeaderComponent.defaultProps = {};

export default HeaderComponent;
