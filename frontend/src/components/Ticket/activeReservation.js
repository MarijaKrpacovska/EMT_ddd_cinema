import React from "react";
import {Link} from 'react-router-dom';

const ActiveReservation = (props) => {

    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"table-responsive"}>

                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>Currency</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>{props.activeReservation.currency}</td>
                        </tr>
                        <tr>
                            <td>{props.activeReservation.paymentMethod}</td>
                        </tr>
                        </tbody>
                    </table>

                    <div className={"row text-center"}>
                        <div className="col buttonDiv">
                        <Link className={"btn btn-success ml-2 mr-4"}
                              onClick={() => props.onConfirmReservation()}
                              to={`/movie`}>
                            Confirm Reservation
                        </Link>
                        </div>

                        <div className="col buttonDiv">
                        <Link className={"btn btn-danger ml-2 mr-4"}
                              onClick={() => props.onCancelReservation()}
                              to={`/movie`}>
                            Cancel Reservation
                        </Link>
                        </div>
                    </div>
                </div>
            </div>
            <div className={"col-md-4 imgDiv"} hidden={"true"}>
                <img id={"imgImg"} className={"imgImg"} height={"1130px"} src={"https://movies.universalpictures.com/media/us-adv1sheet-rgb-2-small-5c1c422026bb0-1-5d3a4e5f87325-1.jpg"}/>
            </div>
        </div>
    );
}

export default ActiveReservation;