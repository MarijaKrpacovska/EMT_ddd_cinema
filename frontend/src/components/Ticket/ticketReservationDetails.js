import React from "react";

const ticketReservationDetails = (props) => {

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
                        <tr>
                        <td>{props.ticketReservation.id.id}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div className={"col-md-4 imgDiv"} hidden={"true"}>
                <img id={"imgImg"} className={"imgImg"} height={"1130px"} src={"https://movies.universalpictures.com/media/us-adv1sheet-rgb-2-small-5c1c422026bb0-1-5d3a4e5f87325-1.jpg"}/>
            </div>
        </div>
    );
}

export default ticketReservationDetails;