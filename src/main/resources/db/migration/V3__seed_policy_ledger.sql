INSERT INTO customers (full_name, email) VALUES
('Anita Sharma', 'anita.sharma@hdfclife.example'),
('Rahul Mehta', 'rahul.mehta@hdfclife.example'),
('Priya Nair', 'priya.nair@hdfclife.example'),
('Arjun Rao', 'arjun.rao@hdfclife.example'),
('Kiran Rao', 'kiran.rao@hdfclife.example');

INSERT INTO policies (policy_no, customer_id, product_type, base_premium, status) VALUES
('HDFC-LIFE-1001', 1, 'TERM', 18500.00, 'Active'),
('HDFC-LIFE-1002', 1, 'ENDOWMENT', 42000.00, 'Active'),
('HDFC-LIFE-1003', 2, 'ULIP', 27000.00, 'Lapsed'),
('HDFC-LIFE-1004', 3, 'TERM', 15200.00, 'Active'),
('HDFC-LIFE-1005', 4, 'ENDOWMENT', 36000.00, 'Active'),
('HDFC-LIFE-1006', 5, 'ULIP', 22000.00, 'Pending');

INSERT INTO policy_riders (policy_id, rider_id) VALUES
(1, 1),
(1, 2);

INSERT INTO claims (claim_no, policy_id, claim_amount, urgency, hospital_name) VALUES
('CLM-01', 1, 15000.00, 'HIGH', 'Apollo Hospital');
