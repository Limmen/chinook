import React, { PropTypes } from 'react';
import withStyles from 'isomorphic-style-loader/lib/withStyles';
import s from './Privacy.scss';

function Privacy() {
    return (
            <div className={s.root}>
            <div className={s.container}>
            <h1 className={s.title}>Privacy</h1>
            </div>
            </div>
    );
}

export default withStyles(Privacy, s);
