-- user
INSERT INTO users (email, nickname, password, created_at, is_deleted, deleted_at)
VALUES
('alice@example.com',   'Alice',   'hashed_pw_1', NOW(), FALSE, NULL),
('bob@example.com',     'Bob',     'hashed_pw_2', NOW(), FALSE, NULL),
('carol@example.com',   'Carol',   'hashed_pw_3', NOW(), FALSE, NULL),
('david@example.com',   'David',   'hashed_pw_4', NOW(), TRUE,  '2025-06-01 10:30:00'),
('eva@example.com',     'Eva',     'hashed_pw_5', NOW(), FALSE, NULL);


-- category
INSERT INTO categories (user_id, name, division, color, icon, created_at, is_deleted, deleted_at)
VALUES
-- [EXPENSE] 지출 카테고리
(1, '식비', 'EXPENSE', '#FF6B6B', 'mdi-food', NOW(), FALSE, NULL),
(1, '교통', 'EXPENSE', '#4ECDC4', 'mdi-bus', NOW(), FALSE, NULL),
(1, '카페', 'EXPENSE', '#FFE66D', 'mdi-coffee', NOW(), FALSE, NULL),
(2, '의료비', 'EXPENSE', '#1A535C', 'mdi-hospital', NOW(), FALSE, NULL),
(2, '쇼핑', 'EXPENSE', '#FF6B6B', 'mdi-shopping', NOW(), FALSE, NULL),
(2, '주거비', 'EXPENSE', '#6B4226', 'mdi-home', NOW(), FALSE, NULL),
(NULL, '통신비', 'EXPENSE', '#B5838D', 'mdi-phone', NOW(), FALSE, NULL),
(NULL, '문화생활', 'EXPENSE', '#FFB4A2', 'mdi-movie', NOW(), FALSE, NULL),
(NULL, '보험료', 'EXPENSE', '#CDB4DB', 'mdi-shield', NOW(), FALSE, NULL),
(NULL, '기타지출', 'EXPENSE', '#9A8C98', 'mdi-currency-usd', NOW(), FALSE, NULL),

-- [INCOME] 수입 카테고리
(1, '월급', 'INCOME', '#2ECC71', 'mdi-cash', NOW(), FALSE, NULL),
(1, '보너스', 'INCOME', '#27AE60', 'mdi-star', NOW(), FALSE, NULL),
(1, '용돈', 'INCOME', '#F1C40F', 'mdi-gift', NOW(), FALSE, NULL),
(2, '투자수익', 'INCOME', '#2980B9', 'mdi-chart-line', NOW(), FALSE, NULL),
(2, '이자소득', 'INCOME', '#8E44AD', 'mdi-bank', NOW(), FALSE, NULL),
(2, '프리랜서수입', 'INCOME', '#34495E', 'mdi-laptop', NOW(), FALSE, NULL),
(NULL, '환급금', 'INCOME', '#E67E22', 'mdi-cash-refund', NOW(), FALSE, NULL),
(NULL, '기타수입', 'INCOME', '#95A5A6', 'mdi-currency-usd', NOW(), FALSE, NULL),
(NULL, '중고거래', 'INCOME', '#D35400', 'mdi-sale', NOW(), FALSE, NULL),
(NULL, '포인트적립', 'INCOME', '#1ABC9C', 'mdi-coin', NOW(), FALSE, NULL);

-- ledger
-- 지출 내역 (25건)
INSERT INTO ledgers (user_id, category_id, division, amount, memo, occurred_at)
VALUES
(1, 1, 'EXPENSE', 12000, '점심 식사', '2025-07-25'),
(1, 2, 'EXPENSE', 2400, '버스 요금', '2025-07-24'),
(1, 3, 'EXPENSE', 5600, '커피', '2025-07-24'),
(1, 7, 'EXPENSE', 15000, '전화요금', '2025-07-23'),
(1, 5, 'EXPENSE', 70000, '의류 쇼핑', '2025-07-22'),
(1, 8, 'EXPENSE', 12000, '영화관람', '2025-07-22'),
(1, 1, 'EXPENSE', 11000, '저녁식사', '2025-07-21'),
(1, 2, 'EXPENSE', 3400, '지하철', '2025-07-21'),
(1, 6, 'EXPENSE', 55000, '전세 관리비', '2025-07-20'),
(1, 9, 'EXPENSE', 95000, '보험 납부', '2025-07-19'),
(1, 3, 'EXPENSE', 4300, '카페라떼', '2025-07-18'),
(1, 1, 'EXPENSE', 13000, '점심 도시락', '2025-07-17'),
(1, 10, 'EXPENSE', 4800, '기타', '2025-07-16'),
(1, 5, 'EXPENSE', 20000, '가방', '2025-07-16'),
(1, 2, 'EXPENSE', 1800, '버스 환승', '2025-07-15'),
(1, 8, 'EXPENSE', 15000, '콘서트 티켓', '2025-07-15'),
(1, 7, 'EXPENSE', 16000, '인터넷 요금', '2025-07-14'),
(1, 3, 'EXPENSE', 5100, '아이스커피', '2025-07-13'),
(1, 1, 'EXPENSE', 10500, '분식', '2025-07-13'),
(1, 6, 'EXPENSE', 89000, '월세', '2025-07-12'),
(1, 4, 'EXPENSE', 23000, '병원 진료', '2025-07-11'),
(1, 9, 'EXPENSE', 78000, '자동차 보험', '2025-07-10'),
(1, 1, 'EXPENSE', 13500, '점심 뷔페', '2025-07-09'),
(1, 10, 'EXPENSE', 2000, '자판기', '2025-07-09'),
(1, 2, 'EXPENSE', 2600, '지하철 요금', '2025-07-08');

-- 수입 내역 (25건)
INSERT INTO ledgers (user_id, category_id, division, amount, memo, occurred_at)
VALUES
(1, 11, 'INCOME', 3000000, '월급', '2025-07-25'),
(1, 12, 'INCOME', 200000, '성과 보너스', '2025-07-20'),
(1, 13, 'INCOME', 50000, '부모님 용돈', '2025-07-20'),
(1, 17, 'INCOME', 35000, '세금 환급', '2025-07-18'),
(1, 14, 'INCOME', 70000, 'ETF 매도', '2025-07-16'),
(1, 15, 'INCOME', 21000, '이자 수입', '2025-07-16'),
(1, 18, 'INCOME', 12000, '중고거래 수입', '2025-07-14'),
(1, 19, 'INCOME', 1500, '포인트 적립', '2025-07-14'),
(1, 16, 'INCOME', 400000, '외주 개발', '2025-07-13'),
(1, 11, 'INCOME', 3000000, '월급', '2025-06-25'),
(1, 13, 'INCOME', 40000, '용돈', '2025-07-05'),
(1, 18, 'INCOME', 22000, '노트북 판매', '2025-07-04'),
(1, 15, 'INCOME', 9000, '적금 이자', '2025-07-03'),
(1, 14, 'INCOME', 88000, '주식 매도', '2025-07-02'),
(1, 12, 'INCOME', 300000, '보너스 지급', '2025-06-30'),
(1, 17, 'INCOME', 45000, '국세 환급', '2025-06-28'),
(1, 16, 'INCOME', 500000, '프리랜서 수입', '2025-06-27'),
(1, 11, 'INCOME', 3000000, '정기 월급', '2025-06-25'),
(1, 19, 'INCOME', 1200, '카드 포인트', '2025-06-23'),
(1, 18, 'INCOME', 17000, '의자 판매', '2025-06-21'),
(1, 14, 'INCOME', 60000, '펀드 수익', '2025-06-20'),
(1, 13, 'INCOME', 30000, '지인 용돈', '2025-06-20'),
(1, 15, 'INCOME', 11000, '예금 이자', '2025-06-18'),
(1, 16, 'INCOME', 420000, '기획 외주', '2025-06-17'),
(1, 19, 'INCOME', 800, '포인트', '2025-06-16');
