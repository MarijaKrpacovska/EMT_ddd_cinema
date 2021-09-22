import React from "react";
import {Link} from 'react-router-dom';

const movies = (props) => {
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>Name</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.movies.map((term) => {
                            return (
                                <tr>
                                    <td>{term.id.id}</td>
                                    <Link className={"btn btn-info ml-2"}
                                       onClick={() => props.onDetails(term.id.id)}
                                       to={`/movie/details/${term.id.id}`}>
                                        Details
                                    </Link>
                                </tr>
                            );
                        })}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}

export default movies;
