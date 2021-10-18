import './pagination.css'
import React from 'react';
import {Link} from 'react-router-dom';

const header = (props) => {
    return (
        <div className="col mb-3">
            <nav aria-label="Page navigation example">
                <ul className="pagination justify-content-center">
                    <li className="page-item"><a className="page-link" href="#">Previous</a></li>

                    <li className="page-item">
                        <Link className="page-link"
                              onClick={() => { props.onPageChange(0,3)}}
                              to={`/movie`}>
                            {props.moviesPage.totalElements}
                        </Link>
                    </li>
                    <li className="page-item">
                        <Link
                            className="page-link"
                            onClick={() => { props.onPageChange(1,3)}}
                            to={`/movie`}>
                            2
                        </Link>
                    </li>
                    <li className="page-item">
                        <Link
                            className="page-link"
                            onClick={() => { props.onPageChange(2,3)}}
                            to={`/movie`}>
                            3
                        </Link>
                    </li>
                    <li className="page-item"><a className="page-link" href="#">Next</a></li>

                </ul>
            </nav>
        </div>
    )
}

export default header;
