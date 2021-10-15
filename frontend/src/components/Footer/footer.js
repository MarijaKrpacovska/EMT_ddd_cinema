import './footer.css'
import React from 'react';
import {Link} from 'react-router-dom';

const footer = (props) => {
    return (
        <footer className="footer mt-auto py-4 bg-dark">
            <div className="container">
                <span className="text-muted">Place sticky footer content here.</span>
            </div>
        </footer>
    )
}

export default footer;
