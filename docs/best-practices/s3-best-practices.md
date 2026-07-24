# S3 Best Practices for CloudBucket

## 1. Bucket Naming Conventions
- Use lowercase, hyphens, and avoid underscores.
- Include environment (dev/staging/prod) and purpose (logs, backups, assets).
- Example: `prod-app-logs-us-east-1`

## 2. Access Control
- **IAM Policies**: Grant least privilege. Use conditions (e.g., `aws:SourceIp`).
- **Bucket Policies**: Restrict public access. Enable "Block Public Access" by default.
- **Pre-signed URLs**: For temporary access to private objects.

## 3. Data Protection
- **Encryption**: Enable SSE-S3 (default) or SSE-KMS for sensitive data.
- **Versioning**: Enable to recover from accidental deletes/overwrites.
- **MFA Delete**: Add extra protection for critical buckets.

## 4. Performance Optimization
- **Prefixes**: Distribute objects across many prefixes (e.g., `YYYY/MM/DD/HH/`).
- **Transfer Acceleration**: Use for large uploads over long distances.
- **Multipart Upload**: For files >100 MB.

## 5. Cost Management
- **Lifecycle Rules**: Transition to S3 Infrequent Access or Glacier after 30 days.
- **Intelligent-Tiering**: Automatically move data based on access patterns.
- **Delete incomplete multipart uploads** after a few days.

## 6. Monitoring & Logging
- **S3 Server Access Logs**: Enable for audit trails (send to separate bucket).
- **AWS CloudTrail**: Track API calls.
- **CloudWatch Metrics**: Monitor `BucketSizeBytes`, `NumberOfObjects`.

## 7. Security Checklist
- [ ] Block all public access.
- [ ] Enable encryption at rest.
- [ ] Enable versioning.
- [ ] Set up lifecycle rules.
- [ ] Configure logging.
- [ ] Review bucket policies quarterly.

---

> **Remember**: A misconfigured S3 bucket is a top security risk. Always validate with tools like `aws s3api get-bucket-policy-status`.