import React from "react";

const movieDetailsWithScheduledMovies = (props) => {

    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <div className={"row"}>
                            <h1 className={"text-danger"}>
                                Current Movies
                            </h1>
                            <hr/>
                            <table className={"table"}>
                                <thead>
                                </thead>
                                <tbody>
                                <td>{props.selectedMovie.description}</td>
                                {props.scheduledMovies.map((term) => {
                                    return (
                                        <tr className={"p-1 mt-5"}>

                                            <td className={"firstTd"}>
                                                <h3 className={"mb-3"}>
                                                    {term.id.id} <small className={"font-weight-light movieGenre"}>({term.genre})</small>
                                                </h3>
                                            </td>
                                        </tr>
                                    );
                                })}
                                <tr></tr>
                                </tbody>
                            </table>
                    </div>
                </div>
            </div>


        </div>
    );
}

export default movieDetailsWithScheduledMovies;