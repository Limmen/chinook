require('normalize.css/normalize.css');
require('styles/App.css');

import React from 'react';
import HeaderComponent from './HeaderComponent';
import FooterComponent from './FooterComponent';
import DataTableComponent from './DataTableComponent';


class AppComponent extends React.Component {
    render() {
        return (
                <div className="index">
                <HeaderComponent />
                <DataTableComponent url="http://localhost:7777/resources/artists" pollInterval="10000" />
                <FooterComponent />
                </div>
        );
    }
}

AppComponent.defaultProps = {
};

export default AppComponent;
