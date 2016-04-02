import React from 'react';
import withStyles from 'isomorphic-style-loader/lib/withStyles';
import s from './Feedback.scss';

function Feedback() {
    return (
            <div className={s.root}>
            <div className={s.container}>
            <a
        className={s.link}
        href="/"
            >TODO: FIX ME</a>
            <span className={s.spacer}>|</span>
            <a
        className={s.link}
        href="/"
            >TODO: FIX ME</a>
            </div>
            </div>
    );
}

export default withStyles(Feedback, s);
