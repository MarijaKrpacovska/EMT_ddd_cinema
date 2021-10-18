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
                            <td>{props.activeReservation.paymentMethod}</td>
                            {/*<td>{*/}
                            {/*    if(items){*/}
                            {/*    props.activeReservation.tickets.map((items) => {*/}
                            {/*    return (*/}
                            {/*        <p>*/}
                            {/*            {items.id.id}*/}
                            {/*        </p>*/}
                            {/*    );*/}
                            {/*})}}</td>*/}
                        </tr>

                        <tr></tr>
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
        </div>
    );
}

export default ActiveReservation;