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

                    <Link className={"btn btn-info ml-2"}
                          onClick={() => props.onConfirmReservation()}
                          to={`/movie`}>
                        Confirm Reservation
                    </Link>

                    <Link className={"btn btn-info ml-2"}
                          onClick={() => props.onCancelReservation()}
                          to={`/movie`}>
                        Cancel Reservation
                    </Link>
                </div>
            </div>
        </div>
    );
}

export default ActiveReservation;