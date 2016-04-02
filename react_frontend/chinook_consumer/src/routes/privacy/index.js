import React from 'react';
import Home from './Privacy';

export const path = '/privacy';
export const action = async (state) => {
    state.context.onSetTitle('Chinook');
    return <Privacy/>;
};
