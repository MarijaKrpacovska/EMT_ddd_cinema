import React from "react";

const moviesDetails = (props) => {

    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <h1>{props.selectedMovie.name}</h1>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                        <img src={props.selectedMovie.url} height={"400px"}></img>

                            <td>{props.selectedMovie.description}</td>
                            <td>{props.selectedMovie.genre}</td>
                            <td>{props.selectedMovie.length}</td>
                        </tr>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}

export default moviesDetails;