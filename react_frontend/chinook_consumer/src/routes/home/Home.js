import React, { PropTypes } from 'react';
import withStyles from 'isomorphic-style-loader/lib/withStyles';
import s from './Home.scss';

function Home() {
    return (
            <div className={s.root}>
            <div className={s.container}>
            <h1 className={s.title}>Chinook</h1>
            </div>
            </div>
    );
}

export default withStyles(Home, s);
