import React from 'react';
import {Form} from 'react-bootstrap';

const TextField=(props)=>{
    var groupClassName=(props.id==="pdfLink")||(props.id==="videoLinks")?"mb-3":"mb-3 required";
    return (
        <div>
            <Form.Group className={groupClassName}>
                <Form.Label className="control-label">{props.name}</Form.Label>
                <Form.Control id={props.id} 
                className="inputsize" 
                type="text" 
                placeholder={props.placeholder} 
                onChange={e=>{props.input(e)}}
                isInvalid={props.isInvalid}
                />
                <Form.Control.Feedback type='invalid'>{props.error}</Form.Control.Feedback>
                <Form.Text className="text-muted">
                </Form.Text>
            </Form.Group>
        </div>
    );
}

export default TextField;