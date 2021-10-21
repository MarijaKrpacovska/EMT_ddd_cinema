import React from "react";
import {Link} from 'react-router-dom';

const ReservationDetails = (props) => {

    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <h3>Your reservation:</h3>
                <hr/>
                <br/>
                <div className={"table-responsive"}>

                    <table className={"table table-striped"}>
                        <thead className={"border-0"}>
                        <tr className={"border-0"}>
                            <th scope={"col"}></th>
                            <th scope={"col"}>Item</th>
                            <th scope={"col"}>Quantity</th>
                            <th scope={"col"}>Price</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props?.activeReservation?.tickets?.map((items) => {
                            return (
                                <tr>
                                    <td>
                                        <img height={"40px"}
                                             src={"https://www.pngkit.com/png/full/81-813494_ticket.png"}/>
                                    </td>
                                    <td>
                                        Ticket
                                    </td>
                                    <td>
                                        {items?.quantity}
                                    </td>
                                    <td>
                                        {items?.price?.amount} {items?.price?.currency}
                                    </td>
                                </tr>
                            );
                        })}

                        <tr></tr>
                        </tbody>
                    </table>

                    <div>
                        <b>Payment method:</b> {props.activeReservation.paymentMethod}
                    </div>
                    <br/>
                    <br/>

                    <div className={"row text-center"}>
                        <div className="col buttonDiv">
                            <Link className={"btn btn-success ml-2 mr-4"}
                                  onClick={() => props.onConfirmReservation(props.activeReservation.id.id)}
                                  to={`/movie`}>
                                Confirm Reservation
                            </Link>
                        </div>

                        <div className="col buttonDiv">
                            <Link className={"btn btn-danger ml-2 mr-4"}
                                  onClick={() => props.onCancelReservation(props.activeReservation.id.id)}
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

export default ReservationDetails;