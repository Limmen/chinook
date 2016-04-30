/**
 * FooterComponent
 */

'use strict';

import React from 'react';

require('styles//Footer.css');

let FooterComponent = (props) => (
  <div className="footer-component">
    <footer className="footer">
      <div className="container">
        <p className="text-muted">Copyright 2016@Kim Hammar</p>
      </div>
    </footer>
  </div>
);

FooterComponent.displayName = 'FooterComponent';

FooterComponent.propTypes = {};
FooterComponent.defaultProps = {};

export default FooterComponent;
