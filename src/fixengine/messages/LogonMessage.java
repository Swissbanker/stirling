/*
 * Copyright 2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fixengine.messages;

import static fixengine.messages.EncryptMethod.NONE;
import fixengine.tags.HeartBtInt;
import fixengine.tags.ResetSeqNumFlag;

/**
 * @author Pekka Enberg 
 */
public class LogonMessage extends AbstractMessage {
    private BooleanField resetSeqNumFlag = new BooleanField(ResetSeqNumFlag.TAG, Required.NO);

    public LogonMessage() {
        this(new MessageHeader(MessageType.LOGON));
    }

    public LogonMessage(MessageHeader header) {
        super(header);

        add(new EncryptMethodField(NONE));
        add(new IntegerField(HeartBtInt.TAG, 30));

        add(resetSeqNumFlag);
    }

    @Override
    public void apply(MessageVisitor visitor) {
        visitor.visit(this);
    }

    public void setResetSeqNumFlag(boolean resetSeqNumFlag) {
        this.resetSeqNumFlag.setValue(resetSeqNumFlag);
    }
}
